/**
 * 
 */
package com.example.libman.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Use a simple base class to hold a key (id) and provide basic implementations
 * of hashCode() and equals().
 * 
 * @author David Pellatt
 */
@MappedSuperclass
public abstract class AbstractEntity<K extends Object> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8262825654393754422L;

	@Id
	private K key;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
