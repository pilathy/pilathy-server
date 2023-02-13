package com.pilathy.domain.rds.domain.account.admin

import com.pilathy.common.TestFixture
import com.pilathy.common.lib.RandomGenerator.generateEmail

@TestFixture
object AdminFixture {

    @JvmOverloads
    @JvmStatic
    fun create(
            email: String = generateEmail(),
            password: String = "password"
    ): Admin = Admin.builder()
            .email(email)
            .password(password)
            .build()

}