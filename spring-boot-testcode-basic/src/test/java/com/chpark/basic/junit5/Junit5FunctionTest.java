package com.chpark.basic.junit5;

import com.chpark.basic.domain.MemberRepository;
import com.chpark.basic.service.MemberService;
import com.chpark.basic.web.MemberController;
import com.chpark.basic.web.dto.MemberJoinRequestDto;
import com.chpark.basic.web.dto.MemberJoinResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.when;

@DataJpaTest
class Junit5FunctionTest {
    @Mock
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberController memberController;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    static void allInit() {
        System.out.println("Junit5FunctionTest.allInit, 테스트시작할때 최초 1회 실행됨");
    }

    @BeforeEach()
    void setup() {
        System.out.println("Junit5FunctionTest.setup, 테스트 메서드 시작될 때마다 실행됨");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Junit5FunctionTest.tearDown, 테스트 메서드 종료될 때마다 실행됨");
    }

    @AfterAll
    static void shutdown() {
        System.out.println("Junit5FunctionTest.shutdown, 테스트종료후 1회 실행됨");
    }

    @Test
    @DisplayName("@Mock은 SpringContainer에 Bean으로 등록되지 않는다")
    void mockIsNotSpringBean() {
        Assertions.assertThatThrownBy(() -> {
            MemberService service = applicationContext.getBean(MemberService.class);
            Assertions.assertThat(service).isNull();
        }).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    @DisplayName("@MockBean은 SpringContainer에 Bean으로 등록된다")
    void mockBeanIsSpringBean() {
        MemberRepository repository = applicationContext.getBean(MemberRepository.class);
        Assertions.assertThat(repository).isNotNull();

        Assertions.assertThat(repository).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("@InjectMock은 @Mock으로 선언된 객체를 조립해서 생성된다")
    void injectMockIsCreatedByMock() {
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto("chpark", 34);
        MemberJoinResponseDto responseDto = new MemberJoinResponseDto(requestDto.toEntity());

        when(memberService.join(requestDto)).thenReturn(responseDto);

        MemberJoinResponseDto joinedMember = memberController.joinMember(requestDto);
        Assertions.assertThat(joinedMember.getName()).isEqualTo("chpark");
        Assertions.assertThat(joinedMember.getAge()).isEqualTo(34);
    }
}
