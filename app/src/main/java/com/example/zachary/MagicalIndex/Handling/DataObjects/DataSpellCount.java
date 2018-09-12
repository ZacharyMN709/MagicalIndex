package com.example.zachary.MagicalIndex.Handling.DataObjects;

/**
 * Created by Zachary on 08-May-17.
 */
public class DataSpellCount {

    String Focus, Ritual, Prepare, SlotLevel, Invocations, Known;
    int Save, Attack;
    int[] Slots;

    public DataSpellCount(int attack, int save, String focus, String ritual, String prepare, String known, int[] slots) {
        Attack = attack;
        Save = save;
        Focus = focus;
        Ritual = ritual;
        Prepare = prepare;
        Known = known;
        Slots = slots;
        SlotLevel = null;
        Invocations = null;
    }

    public DataSpellCount(int attack, int save, String focus, String ritual, String prepare, String known, int[] slots, int slotlevel, int invocations) {
        Attack = attack;
        Save = save;
        Focus = focus;
        Ritual = ritual;
        Prepare = prepare;
        Known = known;
        Slots = slots;
        SlotLevel = String.valueOf(slotlevel);
        Invocations = String.valueOf(invocations);
    }

    public String getAttack() {
        return String.valueOf(Attack);
    }
    public String getSave() {
        return String.valueOf(Save);
    }
    public String getFocus() {
        return Focus;
    }
    public String getRitual() {
        return Ritual;
    }
    public String getPrepare() {
        return Prepare;
    }
    public String getKnown() {
        return Known;
    }
    public int[] getSlots() {
        return Slots;
    }

    public String getSlotLevel() {
        return SlotLevel;
    }
    public String getInvocations() {
        return Invocations;
    }

}
