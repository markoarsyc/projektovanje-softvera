/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.sql.*;
import baza.DBBroker;
import domain.DomainObject;

/**
 *
 * @author Marko
 * @param <T>
 */
public abstract class AbstractSystemOperation<T extends DomainObject<T>> {

    protected DBBroker dbb;

    public AbstractSystemOperation() {
        dbb = new DBBroker();
    }

    public final Object execute(T object) throws Exception {
        try {
            dbb.connect();
            validate(object);
            Object result = executeOperation(object);
            dbb.commit();
            return result;

        } catch (SQLIntegrityConstraintViolationException e) {
            dbb.rollback();
            throw e; // duplicate key

        } catch (IllegalArgumentException e) {
            dbb.rollback();
            throw e; // validacija objekta

        } catch (SQLException e) {
            dbb.rollback();
            throw e; // sve ostale SQL greške

        } catch (Exception e) {
            dbb.rollback();
            throw e; // fallback za sve ostalo
        } finally {
            dbb.disconnect();
        }
        // Svaka SO mora ovo da implementira
    }

    protected abstract void validate(T object) throws Exception;

    protected abstract Object executeOperation(T object) throws Exception;
}
