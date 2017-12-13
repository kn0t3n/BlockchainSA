package com.sabel.IhrName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Philipp Schweiger
 */

public class BlockService {

    private Connection connection;
    private PreparedStatement preparedStatementSelect;


    public BlockService() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:D:\\blockchain.db");
            this.preparedStatementSelect = connection.prepareStatement("SELECT id , timestamp, data, prev_hash, self_hash FROM block");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection = null;
    }

    public List<Block> readAllElements() {
        List<Block> blocks = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                long timestamp = resultSet.getLong(2);
                String data = resultSet.getString(3);
                String prev_hash = resultSet.getString(4);
                Block block = new Block(id, timestamp, data, prev_hash);
                if (resultSet.getString(5).equals(block.getSelf_hash())) {
                    blocks.add(block);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockService that = (BlockService) o;
        return Objects.equals(connection, that.connection) &&
                Objects.equals(preparedStatementSelect, that.preparedStatementSelect);
    }

    @Override
    public int hashCode() {

        return Objects.hash(connection, preparedStatementSelect);
    }

    @Override
    public String toString() {
        return "BlockService{" +
                "connection=" + connection +
                ", preparedStatementSelect=" + preparedStatementSelect +
                '}';
    }

    public static void main(String[] args) {
        BlockService blockService = new BlockService();
        List<Block> allBlocks = blockService.readAllElements();
        blockService.close();
        for(Block block : allBlocks){
            System.out.println(block.getId() + "\t" + block.getPrev_hash() + "\t"+ block.getSelf_hash());
        }
    }
}
