package jpashop.kotlinspring.repository

import jpashop.kotlinspring.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long>{
    fun findAllByName(member: String): MutableList<Member>
}