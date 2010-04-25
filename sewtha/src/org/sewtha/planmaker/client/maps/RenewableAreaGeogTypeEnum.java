package org.sewtha.planmaker.client.maps;

/**
 * Used to describe the type of area that needs to be utilised by the 
 * type of renewable energy. It may well be that all are just polygons - 
 * even points are polygons of a certain diameter.
 * 
 * @author BarnabyK
 *
 */
public enum RenewableAreaGeogTypeEnum {

	POLYGON_SQUARE_METRES, POLYGON_SQUARE_KM, POLYLINE_MILES, POINT;
}
