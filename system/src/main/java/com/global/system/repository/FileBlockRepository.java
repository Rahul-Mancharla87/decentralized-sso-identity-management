package com.global.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.system.blockchain.BlockChainFile;
import com.global.system.blockchain.BlockchainTransaction;

public interface FileBlockRepository extends JpaRepository<BlockChainFile,Long> {

    BlockChainFile findByFileId(int fileId);
    
}
