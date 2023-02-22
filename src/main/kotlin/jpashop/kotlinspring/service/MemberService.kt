package jpashop.kotlinspring.service

import jakarta.transaction.Transactional
import jpashop.kotlinspring.domain.Member
import jpashop.kotlinspring.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {
    @Transactional
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        return memberRepository.save(member).id
    }

    private fun validateDuplicateMember(member: Member) {
        val findMembers: List<Member> = memberRepository.findAllByName(member.name)
        check(findMembers.isEmpty()) { "이미 존재하는 회원입니다." }
    }
}