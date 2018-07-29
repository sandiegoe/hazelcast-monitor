package it.xdnl.hazelcast.monitor.agent.product;

import it.xdnl.chat.typescript.annotation.TypescriptDTO;

@TypescriptDTO
public class SetSummary extends DistributedObjectSummary {
    private int size;

    public SetSummary(int size)  {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}