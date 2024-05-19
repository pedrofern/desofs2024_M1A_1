package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class LogisticsServiceImplTest {

    @Mock
    private PackagingRepository packagingRepo;

    @InjectMocks
    private LogisticsServiceImpl logisticsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testSubmitForm() {
//        // Assuming the submitForm method returns a Packaging object
//        Packaging packaging = new Packaging("1", 2L, 3L, "12:30", "13:30", 1, 2, 3);
//        SubmitLogisticsForm form = new SubmitLogisticsForm();
//
//        doAnswer(invocation -> {
//            Packaging arg = invocation.getArgument(0);
//            assertEquals(packaging, arg);
//            return null;
//        }).when(packagingRepo).save(any(Packaging.class));
//
//        doAnswer(invocation -> {
//            SubmitLogisticsForm arg = invocation.getArgument(0);
//            assertEquals(form, arg);
//            return null;
//        }).when(logisticsService).submitForm(any(SubmitLogisticsForm.class));
//
//    }

    @Test
    public void testGetAllPackaging() {
        Packaging packaging1 = new Packaging("1", 2L, 3L, "12:30", "13:30", 1, 2, 3);
        Packaging packaging2 = new Packaging("2", 3L, 4L, "13:30", "14:30", 2, 3, 4);
        List<Packaging> expectedPackagings = Arrays.asList(packaging1, packaging2);

        when(packagingRepo.findAll()).thenReturn(expectedPackagings);

        List<Packaging> result = logisticsService.getAllPackaging();
        assertEquals(expectedPackagings, result);
    }

    @Test
    public void testSanitizeInput() {
        String input = "<script>alert('Hello');</script>";
        String expectedOutput = "&amp;lt;&amp;#115cript&amp;gt;alert(&amp;#39;Hello&amp;#39;);&amp;lt;/&amp;#115cript&amp;gt;";
        assertEquals(expectedOutput, logisticsService.sanitizeInput(input));
    }

}
