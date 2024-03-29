package Hello.hellospring;
import Hello.hellospring.repository.JdbcMemberRepository;
import Hello.hellospring.repository.MemberRepository;
import Hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final DataSource dataSource;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
    //
}