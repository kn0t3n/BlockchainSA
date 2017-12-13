package com.sabel.IhrName;

import java.util.Objects;
/**
 * @author Philipp Schweiger
 */

public class Block {

    private int id;
    private long timestamp;
    private String data, prev_hash, self_hash;


    public Block() {
    }

    public Block(int id, long timestamp, String data, String prev_hash) {
        this.id = id;
        this.timestamp = timestamp;
        this.data = data;
        this.prev_hash = prev_hash;
        this.self_hash = hashBlock();

    }

    private String hashBlock() {
        String toHash =Integer.toString(id) + Long.toString(timestamp) + data + prev_hash;
        return Util.hashFunction(toHash);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrev_hash() {
        return prev_hash;
    }

    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }

    public String getSelf_hash() {
        return self_hash;
    }

    public void setSelf_hash(String self_hash) {
        this.self_hash = self_hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return id == block.id &&
                Long.compare(block.timestamp, timestamp) == 0 &&
                Objects.equals(data, block.data) &&
                Objects.equals(prev_hash, block.prev_hash) &&
                Objects.equals(self_hash, block.self_hash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, timestamp, data, prev_hash, self_hash);
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", data='" + data + '\'' +
                ", prev_hash='" + prev_hash + '\'' +
                ", self_hash='" + self_hash + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Block block = new Block(13, 1512457369, "Start:", "0");
        System.out.println(block);
    }
}
