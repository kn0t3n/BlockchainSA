package com.sabel.IhrName;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Philipp Schweiger
 */
public class Main {
    public static void main(String[] args) {
        String ihrName = "Schweiger";
        System.out.println(ihrName + ", SHA-256-Hashwert: " + Util.hashFunction(ihrName));

        GBSBlockchain blockchain = new GBSBlockchain();
        blockchain.printData();
    }
}
