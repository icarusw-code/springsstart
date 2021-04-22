package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test가 끝나면 클리어 해주는 코드가 필요 (순서에 의존해서 test를 돌리면 안되기 떄문)

   @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("test");

        repository.save(member);

        //optional 에서 꺼낼때 get()을 사용
        Member result = repository.findById(member.getId()).get();

        /* soutv를 입력하면 자동완성 가능
        System.out.println("result = " + (result == member));
        이기능도 있지만 assert라는 기능으로 텍스트 입력을 줄일 수 있다.
         */

        /*앞에 기대값 뒤에 결과 junit
        Assertions.assertEquals(member , result);
         */

        //import 시키면 assertions 안치고 바로 assertThat 사용 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + F6 하면 동일한 이름 바꾸기 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
