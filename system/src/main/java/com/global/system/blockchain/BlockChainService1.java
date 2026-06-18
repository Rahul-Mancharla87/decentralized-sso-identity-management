package com.global.system.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.system.model.FileModel;
import com.global.system.model.UserModel;
import com.global.system.repository.FileBlockRepository;
import com.global.system.repository.FileRepository;
import com.global.system.repository.Userrepository;
import com.global.system.utils.HashCodeGenearate;

import java.io.IOException;
import java.math.BigInteger;

@Service
public class BlockChainService1 {

    @Autowired
    Userrepository userrepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileBlockRepository fileBlockRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockChainService1.class);

   

    public BlockChainFile process(BlockChainFile trx) throws IOException {

        UserModel um=userrepository.findById(trx.getUserId()).get();
       
        String hash=HashCodeGenearate.getHashValue(trx.getOldFileHash());

        trx.setNewFileHash(hash);

        return  fileBlockRepository.save(trx);
      

    }

}

