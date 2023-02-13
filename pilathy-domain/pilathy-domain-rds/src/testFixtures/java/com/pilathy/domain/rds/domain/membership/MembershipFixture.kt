package com.pilathy.domain.rds.domain.membership

import com.pilathy.common.TestFixture
import com.pilathy.common.lib.RandomGenerator.generateInt
import com.pilathy.domain.rds.domain.account.user.User
import com.pilathy.domain.rds.domain.center.Center
import java.time.LocalDate

@TestFixture
object MembershipFixture {

    @JvmOverloads
    @JvmStatic
    fun create(
            startDate: LocalDate = LocalDate.of(2023, 1, 2),
            endDate: LocalDate = LocalDate.of(2023, 4, 2),
            remainCount: Int = generateInt(1, 50),
            user: User,
            center: Center
    ): Membership = Membership.builder()
            .startDate(startDate)
            .endDate(endDate)
            .remainCount(remainCount)
            .user(user)
            .center(center)
            .build()

}