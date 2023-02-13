package com.pilathy.domain.rds.domain.center

import com.pilathy.common.TestFixture
import com.pilathy.common.lib.RandomGenerator.generatePhone
import com.pilathy.common.lib.RandomGenerator.generateString
import com.pilathy.domain.rds.domain.account.admin.Admin
import com.pilathy.domain.rds.domain.common.Address

@TestFixture
object CenterFixture {

    @JvmOverloads
    @JvmStatic
    fun create(
            name: String = "Center${generateString()}",
            address: Address = Address.of(generateString(10000, 99999), "서울", "어딘가"),
            phone: String = generatePhone(),
            img: String = "Image${generateString()}",
            description: String = "Description${generateString()}",
            admin: Admin
    ): Center = Center.builder()
            .name(name)
            .address(address)
            .phone(phone)
            .img(img)
            .description(description)
            .admin(admin)
            .build()

}