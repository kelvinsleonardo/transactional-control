package br.com.kelvinsantiago.transactionalcontrol.rest;

import br.com.kelvinsantiago.transactionalcontrol.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/balances/by-id/{id}")
    public Object saveById(@PathVariable long id) {
        return balanceService.saveValueById(id);
    }

    @GetMapping("/balances/by-name/{name}")
    public Object saveByName(@PathVariable String name) {
        return balanceService.saveValueByName(name);
    }
}
