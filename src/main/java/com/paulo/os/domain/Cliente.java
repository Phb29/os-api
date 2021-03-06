package com.paulo.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class Cliente extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "cliente")
	 private  List<OS>  list= new  ArrayList<>();

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer id, String nome, String cpf, String email) {
		super(id, nome, cpf, email);
		
	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}
	

}