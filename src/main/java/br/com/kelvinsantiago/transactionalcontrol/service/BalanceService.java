package br.com.kelvinsantiago.transactionalcontrol.service;

import br.com.kelvinsantiago.transactionalcontrol.repository.BalanceRepository;
import com.diogonunes.jcolor.Attribute;
import com.github.javafaker.Faker;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.BRIGHT_RED_TEXT;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private static int toggle = 0;

    @SneakyThrows
    @Transactional
    public Object saveValueById(@NotNull Long id) {
        var colorText = getTextColor();
        var threadId = Thread.currentThread().getId();
        log.info(colorize("{}:: Running method saveValueById", colorText), threadId);
        Thread.sleep(1000);
        var data = balanceRepository.findByIdLock(id).map(balance -> {
            var randomNumber = new Random().nextInt();
            log.info(colorize("{}:: Founded {}", colorText), threadId, balance);
            log.info(colorize("{}:: Random new number {} ", colorText), threadId, randomNumber);
            balance.setValue(randomNumber);
            balanceRepository.save(balance);
            log.info(colorize("{}:: Saved {}", colorText), threadId, balance);
            return balance;
        });
        Thread.sleep(4000);
        log.info(colorize("{}:: save {}", colorText),threadId, data);
        return data;
    }

    @SneakyThrows
    @Transactional
    public Object saveValueByName(@NotNull String name) {
        Faker faker = new Faker();
        var colorText = getTextColor();
        var threadId = Thread.currentThread().getId();
        log.info(colorize("Thread {}:: Running method saveValueByName", colorText), threadId);
        Thread.sleep(1000);
        var data = balanceRepository.findByNameLock(name).map(balance -> {
            log.info(colorize("Thread {}:: Founded {}", colorText), threadId, balance);
            balance.setName(faker.name().firstName().toUpperCase().trim());
            balanceRepository.save(balance);
            log.info(colorize("Thread {}:: Saved {}", colorText), threadId, balance);
            return balance;
        });
        Thread.sleep(4000);
        log.info(colorize("Thread {}:: Finish transaction with {}", colorText),threadId, data);
        return data;
    }


    @Synchronized
    static Attribute getTextColor() {
        var color = BRIGHT_RED_TEXT();
        if (toggle == 0) {
            color = BRIGHT_YELLOW_TEXT();
            toggle = 1;
        } else {
            toggle = 0;
        }
        return color;
    }

}
