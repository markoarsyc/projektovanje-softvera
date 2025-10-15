/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import domain.DomainObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marko
 * @param <T>
 */
public class VratiSveSO<T extends DomainObject<T>> extends AbstractSystemOperation<T> {

    @Override
    protected void validate(T object) throws Exception {
        //Ova operacija ne zahteva validaciju
    }

    @Override
    protected Object executeOperation(T object) throws Exception {
        List<T> result = (List<T>) dbb.selectAll(object);
        return result != null ? result : new ArrayList<T>();
    }

}
