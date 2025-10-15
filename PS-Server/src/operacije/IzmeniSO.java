/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import domain.DomainObject;

/**
 *
 * @author Marko
 * @param <T>
 */
public class IzmeniSO<T extends DomainObject<T>> extends AbstractSystemOperation<T> {

    @Override
    protected void validate(DomainObject object) throws Exception {
        if (object == null) {
            throw new Exception("Objekat za izmenu ne sme biti null.");
        }
    }

    @Override
    protected Object executeOperation(DomainObject object) throws Exception {
        return dbb.update(object);
    }

}
