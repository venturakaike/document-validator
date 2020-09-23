package com.innovation.validator.test.unit;

import com.innovation.validator.configuration.MockitoExtension;
import com.innovation.validator.core.service.impl.CPFServiceImpl;
import com.innovation.validator.core.util.Message;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

@ExtendWith({MockitoExtension.class})
public class CPFServiceTest {

    @Spy
    @InjectMocks
    private CPFServiceImpl cpfService;

    @Mock
    private Message message;

}