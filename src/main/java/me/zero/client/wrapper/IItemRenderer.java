package me.zero.client.wrapper;

import net.minecraft.util.EnumHandSide;

/**
 * Gives access to first person item
 * transformation methods.
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public interface IItemRenderer {

    /**
     * Calls ItemRenderer#transformSideFirstPerson(EnumHandSide, float)
     *
     * @param side The side being transformed for
     * @param rechargeProgress The item recharge progress
     */
    void setupSideFirstPerson(EnumHandSide side, float rechargeProgress);

    /**
     * Calls ItemRenderer#transformFirstPerson(EnumHandSide, float)
     *
     * @param side The side being transformed for
     * @param swingProgress The item swing progress
     */
    void setupFirstPerson(EnumHandSide side, float swingProgress);
}
