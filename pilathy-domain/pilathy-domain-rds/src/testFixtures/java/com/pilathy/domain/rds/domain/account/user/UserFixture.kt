package com.pilathy.domain.rds.domain.account.user

import com.pilathy.common.TestFixture
import com.pilathy.common.lib.RandomGenerator.generateEmail
import com.pilathy.common.lib.RandomGenerator.generateInt
import com.pilathy.common.lib.RandomGenerator.generateLocalDate
import com.pilathy.common.lib.RandomGenerator.generatePhone
import java.time.LocalDate

@TestFixture
object UserFixture {

    @JvmOverloads
    @JvmStatic
    fun create(
            email: String = generateEmail(),
            password: String = "1",
            name: String = "User${generateInt()}",
            birthDate: LocalDate = generateLocalDate(),
            phone: String = generatePhone(),
    ) = User.builder()
            .email(email)
            .password(password)
            .name(name)
            .birthDate(birthDate)
            .phone(phone)
            .build()

}