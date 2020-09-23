package com.innovation.validator.test.unit;

import com.innovation.validator.configuration.MockitoExtension;
import com.innovation.validator.core.service.impl.CNPJServiceImpl;
import com.innovation.validator.core.util.Message;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

@ExtendWith({MockitoExtension.class})
public class CNPJServiceTest {

    @Spy
    @InjectMocks
    private CNPJServiceImpl cnpjService;

    @Mock
    private Message message;



}
