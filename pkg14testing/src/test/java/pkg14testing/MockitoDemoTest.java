package pkg14testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MockitoDemoTest {

    interface Repository { String findById(String id); }

    @Mock Repository repo;

    @Test
    void mockReturnsStubbedValue() {
        when(repo.findById("42")).thenReturn("Ada");
        assertEquals("Ada", repo.findById("42"));
        verify(repo).findById("42");
        verifyNoMoreInteractions(repo);
    }
}
