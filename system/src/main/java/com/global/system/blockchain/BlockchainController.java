/* package com.global.system.blockchain;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class BlockchainController {

    private final BlockchainService service;

    public BlockchainController(BlockchainService service) {
        this.service = service;
    }

    @PostMapping("/transaction")
    public BlockchainTransaction execute(@RequestBody BlockchainTransaction transaction) throws IOException {
        return service.process(transaction);
    }

}
 */