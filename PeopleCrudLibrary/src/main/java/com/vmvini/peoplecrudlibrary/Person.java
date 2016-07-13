/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudlibrary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
/**
 *
 * @author marcusviniv
 */
@Entity
@Table(name = "TB_Pessoa")
@SequenceGenerator(name = "pessoa_id",allocationSize = 5)
@TableGenerator(name = "tabela_id",
        table = "GENERATOR",
        pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT",
        
        pkColumnValue = "SEQ_GEN")

public class Person implements Serializable{
    
    
    //USAR CHAVES COMPOSTAS 
    //USAR EMBEBED
    //USAR ACCESS NO METODO 
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "tabela_id")
    private int codigo;
    
    @Column(name = "nome_Completo",length = 60)
    private String nome ;
    
    @Transient
    private int idade;

    @Temporal(TemporalType.DATE)
    private LocalDate dateNascimento;
    
    //CRIAR CONVERTER PARA sql time
    @Temporal(TemporalType.TIME)
    private LocalDate horarioNascimento;
    
    
    @Enumerated(EnumType.STRING)
    private SexoType sexoType;

    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] arquivoPDF;

    
    
    public byte[] getArquivoPDF() {
        return arquivoPDF;
    }

    public void setArquivoPDF(byte[] arquivoPDF) {
        this.arquivoPDF = arquivoPDF;
    }

    public String getTextoGigante() {
        return textoGigante;
    }

    public void setTextoGigante(String textoGigante) {
        this.textoGigante = textoGigante;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
    
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String textoGigante;
    
    @ElementCollection
    private List<String> emails;
    
    public Person() {
    }

    public Person(String nome, int idade) {
        this(0, nome, idade);
    }

    public Person(int codigo, String nome, int idade) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
    }
    

    public void setSexoType(SexoType s){
        this.sexoType = s;
    }
    
    public SexoType getSexoType(){
        return sexoType;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public LocalDate getDateNascimento() {
        return dateNascimento;
    }

    public void setDateNascimento(LocalDate dateNascimento) {
        this.dateNascimento = dateNascimento;
    }

    public LocalDate getHorarioNascimento() {
        return horarioNascimento;
    }

    public void setHorarioNascimento(LocalDate horarioNascimento) {
        this.horarioNascimento = horarioNascimento;
    }
    
    
    
}
