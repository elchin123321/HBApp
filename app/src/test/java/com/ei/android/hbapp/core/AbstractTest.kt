package com.ei.android.hbapp.core

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.lang.Exception

class AbstractTest{

    @Test
    fun test_success(){
        val dataObject = TestDataObject.Success("A","B")
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }
    @Test
    fun test_fail(){
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObject.Failure)
    }

    private sealed class TestDataObject : Abstract.Object<DomainObject,DataToDomainMapper> {
        abstract override fun map(mapper: DataToDomainMapper): DomainObject

        class Success(private val textOne:String,
                      private val textTwo:String
        ):TestDataObject(){
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(textOne,textTwo)
            }

        }

        class Fail(private val e:Exception):TestDataObject(){
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(e)
            }

        }
    }
    private interface DataToDomainMapper:Abstract.Mapper{
        fun map(textOne: String,textTwo: String):DomainObject
        fun map(e:Exception):DomainObject

        class Base:DataToDomainMapper{
            override fun map(textOne: String, textTwo: String): DomainObject {
                return DomainObject.Success("$textOne $textTwo")
            }

            override fun map(e: Exception): DomainObject {
                return DomainObject.Failure()
            }

        }
    }
    private sealed class  DomainObject:Abstract.Object<UiObject, DomainToUiMapper>{
        class Success(private val textCombined:String):DomainObject(){
            override fun map(mapper: DomainToUiMapper): UiObject {
                TODO("Not yet implemented")
            }
        }
        class Failure():DomainObject(){
            override fun map(mapper: DomainToUiMapper): UiObject {
                TODO("Not yet implemented")
            }

        }

    }
    private interface DomainToUiMapper:Abstract.Mapper

    private sealed class UiObject:Abstract.Object<Unit, Abstract.Mapper>
}


