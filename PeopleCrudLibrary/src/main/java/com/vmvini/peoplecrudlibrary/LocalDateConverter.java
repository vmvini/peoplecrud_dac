/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudlibrary;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author vmvini
 */

@Converter(autoApply=true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate x) {
        Date date = Date.valueOf(x);
        return date;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date y){
        return y.toLocalDate();
    }
    
}
