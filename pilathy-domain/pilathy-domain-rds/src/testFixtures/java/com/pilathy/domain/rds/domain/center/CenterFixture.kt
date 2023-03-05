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
            zipcode: String = generateString(10000, 99999),
            defaultAddress: String = "경기 성남시 분당구 판교역로 166",
            detailAddress: String = "카카오판교아지트 ${generateString(100, 999)}층",
            phone: String = generatePhone(),
            img: String = "Image${generateString()}",
            description: String = "Description${generateString()}",
            admin: Admin
    ): Center = Center.builder()
            .name(name)
            .zipcode(zipcode)
            .defaultAddress(defaultAddress)
            .detailAddress(detailAddress)
            .phone(phone)
            .img(img)
            .description(description)
            .admin(admin)
            .build()

}