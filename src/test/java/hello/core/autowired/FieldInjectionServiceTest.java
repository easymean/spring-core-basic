package hello.core.autowired;

import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

public class FieldInjectionServiceTest {

    @Test
    void testFieldInjection(){
        FieldInjectionService fieldInjectionService = new FieldInjectionService();
        fieldInjectionService.setMemberRepository(new MemoryMemberRepository());
        fieldInjectionService.printFieldInjection(1L);
    }
}
