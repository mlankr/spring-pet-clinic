package com.turing.springpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Milan on 2023/02/14.
 */

@Getter
@Setter
public class BaseEntity implements Serializable {

    private Long id;
}
