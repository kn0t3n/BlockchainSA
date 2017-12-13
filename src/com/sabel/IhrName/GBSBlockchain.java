package com.sabel.IhrName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philipp Schweiger
 */

public class GBSBlockchain {

    private List<Block> blockList;


    public GBSBlockchain() {
        this.blockList = new ArrayList<>();
        this.readBlocks();
    }

    private void readBlocks() {
        BlockService blockService = new BlockService();
        List<Block> allBlocks = blockService.readAllElements();
        blockService.close();
        Block block0 = findNextBlock("0", allBlocks);
        blockList.add(block0);
        Block nextBlock = block0;
        while ((nextBlock = findNextBlock(nextBlock.getSelf_hash(), allBlocks)) != null) {
            blockList.add(nextBlock);
        }
    }

    public void printData() {
        for (Block block : blockList) {
            System.out.print(block.getData() + " ");
        }
        System.out.println();
    }

    private Block findNextBlock(String hash, List<Block> blocks) {
        for (Block block : blocks) {
            if (block.getPrev_hash().equals(hash)) {
                return block;
            }
        }
        return null;
    }

}
