/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sewtha.planmaker.client;

import com.google.gwt.user.client.ui.Composite;

/**
 *
 * @author BarnabyK
 */
/**
 * An 'Inspector' is the panel that prorvides the details on the energy choice.
 * Lazily instantiated so that the application doesn't pay for all of them on startup.
 */
public abstract class Inspector extends Composite {

  /**
   * Encapsulated information about a sink. Each sink is expected to have a
   * static <code>init()</code> method that will be called by the kitchen sink
   * on startup.
   */
  public abstract static class InspectorInfo {
    private Inspector instance;
    private String name, description;

    public InspectorInfo(String name, String desc) {
      this.name = name;
      description = desc;
    }

    public abstract Inspector createInstance();

    public String getDescription() {
      return description;
    }

    public final Inspector getInstance() {
      if (instance != null) {
        return instance;
      }
      return (instance = createInstance());
    }

    public String getName() {
      return name;
    }
  }

  /**
   * Called just before this sink is hidden.
   */
  public void onHide() {
  }

  /**
   * Called just after this sink is shown.
   */
  public void onShow() {
  }
}

