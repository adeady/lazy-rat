package com.iai

import javax.persistence.Entity
import javax.persistence.Id;

@Entity
class User {

    @Id Long id

    String username

    String email
}
