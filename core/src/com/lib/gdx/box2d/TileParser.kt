package com.lib.gdx.box2d

import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.*
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTile
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ObjectMap
import com.lib.gdx.util.f
import com.lib.gdx.util.v

object TileParser {

    /**
     * Find all tiles containing objects in a tiled map in layer
     * @param world
     * @param map
     * @param layerName
     * @param callback
     */
    inline fun parseTileObjects(world: World, map: TiledMap, layerName: String, callback: Body.(ObjectMap<String, String>) -> Unit = { }) {
        findTileObjects(map, layerName).forEach {
            val x = it.key.first
            val y = it.key.second
            val tile = it.value

            tile.objects.forEach { mapObject ->
                when(mapObject) {
                    is RectangleMapObject -> parseRectangle(world, mapObject, x, y, callback)
                    is PolylineMapObject -> parsePolyline(world, mapObject, x, y, callback)
                    is CircleMapObject -> parseCircle(world, mapObject, x, y, callback)
                    is EllipseMapObject -> parseEllipse(world, mapObject, x, y, callback)
                    is PolygonMapObject -> parsePolygon(world, mapObject, x, y, callback)
                }
            }
        }
    }

    /**
     * Find all objects in a tiled map in layer
     * @param map
     * @param layerName
     */
    fun parseLayerObjects(world: World, map: TiledMap, layerName: String,
                          callback: Body.(ObjectMap<String, String>) -> Unit = { }) {
//        val objectsType = listOf(PolylineMapObject::class, RectangleMapObject::class)
//        val mapObjects = map.layers[layerName].objects
//
//        fun <T: MapObject> aha(type: KClass<T>) {
//            val array = Array<T>()
//            mapObjects.getByType(type.java, array)
//            array.forEach { (it as type).parse(world) }
//        }
//
//       mapObjects.getByType(objectsType[0].java).forEach { it }

        map.layers[layerName].objects.forEach {
            when (it) {
                is RectangleMapObject -> parseRectangle(world, it, 0f, 0f, callback)
                is PolylineMapObject -> parsePolyline(world, it, 0f, 0f, callback)
                is CircleMapObject -> parseCircle(world, it, 0f, 0f, callback)
                is EllipseMapObject -> parseEllipse(world, it, 0f, 0f, callback)
                is PolygonMapObject -> parsePolygon(world, it, 0f, 0f, callback)
            }
        }
    }

    /**
     * Find layer properties
     * @param map
     * @param layerName
     * @return Array of object map properties <String, String>
     */
    fun parseLayerProperties(map: TiledMap, layerName: String): Array<ObjectMap<String, String>> {
        val results = Array<ObjectMap<String, String>>()

        map.layers[layerName].objects.forEach { results.add(findProperties(it)) }

        return results
    }

    /**
     * Find tile properties
     * @param map
     * @param layerName
     * @return Array of object map properties <String, String>
     */
    fun parseTileProperties(map: TiledMap, layerName: String): Array<ObjectMap<String, String>> {
        val results = Array<ObjectMap<String, String>>()

        findTileObjects(map, layerName).forEach { it.value.objects.forEach { results.add(findProperties(it)) }}

        return results
    }

    /* Parse geometries */

    inline fun parsePolygon(world: World, polygonObject: PolygonMapObject, idx: Float, idy: Float,
                            init: Body.(ObjectMap<String, String>) -> Unit) {
        val polygon = polygonObject.polygon
        val x = polygon.x + idx
        val y = polygon.y + idy
        val bDef = BodyDef()
        val fDef = FixtureDef()
        val shape = PolygonShape()

        shape.set(polygon.vertices
                .mapIndexed { index, vertex -> if (index % 2 == 0) (x + vertex).v else (y + vertex).v  }.toFloatArray())
        fDef.shape = shape
        world.createBody(bDef).createFixture(fDef).body.init(findProperties(polygonObject))
        shape.dispose()
    }

    inline fun parseEllipse(world: World, ellipseObject: EllipseMapObject, idx: Float, idy: Float,
                            init: Body.(ObjectMap<String, String>) -> Unit) {
        val ellipse = ellipseObject.ellipse
        val x = ellipse.x + idx + ellipse.width / 2
        val y = ellipse.y + idy + ellipse.width / 2
        val bDef = BodyDef()
        val fDef = FixtureDef()
        val shape = CircleShape()

        bDef.position.set(x.v, y.v)
        shape.radius = ellipse.width.v / 2
        fDef.shape = shape
        world.createBody(bDef).createFixture(fDef).body.init(findProperties(ellipseObject))
        shape.dispose()
    }

    inline fun parseCircle(world: World, circleObject: CircleMapObject, idx: Float, idy: Float, init: Body.(ObjectMap<String, String>) -> Unit) {
        val circle = circleObject.circle
        val x = circle.x + idx + circle.radius
        val y = circle.y + idy + circle.radius
        val bDef = BodyDef()
        val fDef = FixtureDef()
        val shape = CircleShape()

        bDef.position.set(x.v, y.v)
        shape.radius = circle.radius.v
        fDef.shape = shape
        world.createBody(bDef).createFixture(fDef).body.init(findProperties(circleObject))
        shape.dispose()
    }

    inline fun parsePolyline(world: World, polylineObject: PolylineMapObject, idx: Float, idy: Float, init: Body.(ObjectMap<String, String>) -> Unit) {
        val polyline = polylineObject.polyline
        val x = polyline.x + idx
        val y = polyline.y + idy
        val bDef = BodyDef()
        val fDef = FixtureDef()
        val shape = ChainShape()

        shape.createChain(polyline.vertices.mapIndexed {
            index, vertices -> if (index % 2 == 0) (x + vertices).v else (y + vertices).v
        }.toFloatArray())
        fDef.shape = shape
        world.createBody(bDef).createFixture(fDef).body.init(findProperties(polylineObject))
        shape.dispose()
    }

    inline fun parseRectangle(world: World, rectangleObject: RectangleMapObject, idx: Float, idy: Float, init: Body.(ObjectMap<String, String>) -> Unit) {
        val rectangle = rectangleObject.rectangle
        val x = rectangle.x + idx + rectangle.width / 2
        val y = rectangle.y + idy + rectangle.height / 2
        val bDef = BodyDef()
        val fDef = FixtureDef()
        val shape = PolygonShape()

        bDef.position.set(x.v, y.v)
        shape.setAsBox(rectangle.width.v / 2, rectangle.height.v / 2)
        fDef.shape = shape
        world.createBody(bDef).createFixture(fDef).body.init(findProperties(rectangleObject))
        shape.dispose()
    }

    fun findTileObjects(map: TiledMap, layerName: String): ObjectMap<Pair<Float, Float>, TiledMapTile> {
        val layer = map.layers[layerName] as TiledMapTileLayer
        val results = ObjectMap<Pair<Float, Float>, TiledMapTile>()
        var i = -1
        var j = -1

        while (++i < layer.width) {
            while (++j < layer.height) {
                val cell = layer.getCell(i, j)
                if (cell != null && cell.tile.objects.count > 0) {
                    results.put(i * layer.tileWidth.f to j * layer.tileHeight.f, cell.tile)
                }
            }
            j = -1
        }

        return results
    }

    /* Other */

    /**
     * Get properties from map object
     * @param mapObject
     * @return properties
     */
    fun findProperties(mapObject: MapObject): ObjectMap<String, String> {
        val properties = ObjectMap<String, String>()
        mapObject.properties.keys.forEach { properties.put(it, mapObject.properties[it].toString()) }
        return properties
    }
}


