package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.PayementSessionDto;
import edu.esprit.kaddem.lib.AbstractCrudController;
import edu.esprit.kaddem.model.PaymentSession;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/paysession", produces = "application/json")
public class PayementSessionController extends AbstractCrudController<PaymentSession,PayementSessionDto> {


}
