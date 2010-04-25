/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sewtha.planmaker.client;


/**
 *
 * @author BarnabyK
 * @date 
 */
public interface EnergyChangeListener {

    /**
     * Fired if a new energy source is changed and informs all listeners that the passed in source is the
     * changed one.
     * @param changedSource
     */
    public void EnergySourceChanged(EnergyProducerOrConsumer changedSource);
    
    /**
     * Fired if one of the energy sources or consumers has a change to their value
     * @param metricValue
     * @param KWHdpValue
     */
    public void EnergyValueChanged (double metricValue, double KWHdpValue, 
    		EnergyProducerOrConsumer changedProdOrCons);
    
    public void EnergyPlanChanged();
    
}
