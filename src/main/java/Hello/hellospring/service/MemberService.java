package Hello.hellospring.service;

import Hello.hellospring.domain.Member;
import Hello.hellospring.repository.MemberRepository;
import Hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 단축키! cmd + shift + T 테스트 자동생성
// 단순 Java파일은 Spring이 인식하기 어렵기 때문에 @Service로 명시해준다.

public class MemberService {
    private final MemberRepository memberRepository;


        public MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        /*회원가입*/
    public Long join(Member member){

        // 단축키! Cmd + Opt + V = Optional 생성
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());

        // if(result == null)을 Optional로 감싸고 ifPresent로 편하게 사용할 수 있다.
        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); */

       // 단축키! ctrl + T 메소드 추출
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        // 새로 변수에 지정하지 않고, 직접 .ifPresent를 붙여서 활요할 수도 있다.
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
    }
    //전체회원 조회
    public List<Member> findMembers(){
         return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
