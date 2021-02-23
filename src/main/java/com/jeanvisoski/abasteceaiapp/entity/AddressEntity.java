package com.jeanvisoski.abasteceaiapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private String cep;

    private String endereco;

    private Integer numero;

    private String cidade;

    private String bairro;

    private String uf;
}
