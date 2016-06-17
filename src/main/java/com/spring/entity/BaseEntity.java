package com.spring.entity;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract int getId();

	public abstract void setId(int id);

}
