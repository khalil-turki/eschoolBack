package edu.esprit.kaddem.lib;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<T> extends AbstractPersistable<Integer> implements Serializable {
    public void setId(Integer id) {
        super.setId(id);
    }
}
