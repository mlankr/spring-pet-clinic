package com.turing.springpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Milan on 2023/02/14.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private Long id;
}
