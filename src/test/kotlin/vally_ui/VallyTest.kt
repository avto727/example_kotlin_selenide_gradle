package vally_ui

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By
import com.codeborne.selenide.Configuration
import vally_ui.Elements.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)



class VallyTest {
    var elem: Map<String, SelenideElement> = mapOf(
            "Кнопка_меню_Кино" to elements(By.tagName("a")).findBy(text("Кино")),
            "Кнопка_меню_Сериалы" to elements(By.tagName("a")).findBy(text("Сериалы")),
            "Кнопка_меню_ТВ_шоу" to elements(By.tagName("a")).findBy(text("ТВ шоу")),
            "Кнопка_меню_ТВ_каналы" to elements(By.tagName("a")).findBy(text("ТВ каналы")),
            "Кнопка_меню_Детям" to elements(By.tagName("a")).findBy(text("Детям")),
            "Текст_подзаголовка_страницы" to element(By.xpath("//div[@class='site-page-header']//span//h2")),
    )
    var elems: Map<String, ElementsCollection> = mapOf(
            "Текст_выпадающего_меню" to elements(By.xpath("//div[@class='categories-select__categories']//a[1]")), // Первый пункт списка
            "Все_пункты_выпадающего_меню" to elements(By.xpath("//div[@class='categories-select__categories']//a"))
    )

    @BeforeAll
    fun setUp() {
//        System.setProperty("selenide.browser", "chrome")
//        System.setProperty("selenide.browser", "firefox")
        Configuration.headless = false
        open("https://vally.tv")

    }

        @Test
    fun C1_headerMenu() {
//        Проверка переходов по разделам верхнего меню
        hoveR("Кнопка_меню_Кино", 1)
        shHaves("Текст_выпадающего_меню", "Боевик", 2)
        elClick("Кнопка_меню_Кино", 3)
        shHave("Текст_подзаголовка_страницы", "Кино", 4)

        hoveR("Кнопка_меню_Сериалы", 5)
        shHaves("Текст_выпадающего_меню", "Cериалы", 6, 1)
        elClick("Кнопка_меню_Сериалы", 7)
        shHave("Текст_подзаголовка_страницы", "Сериалы", 8)

        hoveR("Кнопка_меню_ТВ_шоу", 9)
        shHaves("Текст_выпадающего_меню", "Stand up шоу", 10, 2)
        elClick("Кнопка_меню_ТВ_шоу", 11)
        shHave("Текст_подзаголовка_страницы", "ТВ шоу", 12)

        hoveR("Кнопка_меню_ТВ_каналы", 13)
        shHaves("Текст_выпадающего_меню", "Музыкальные телеканалы", 14, 3)
        elClick("Кнопка_меню_ТВ_каналы", 15)
        shHave("Текст_подзаголовка_страницы", "ТВ каналы", 16)

        hoveR("Кнопка_меню_Детям", 17)
        shHaves("Текст_выпадающего_меню", "Детское ТВ", 18, 4)
        elClick("Кнопка_меню_Детям", 19)
        shHave("Текст_подзаголовка_страницы", "Детям", 20)

//        Проверка в цикле всех полей выпадающего меню


//        проверка переходов по ссылкам выпадающих меню (при наведении курсора на раздел верхнего меню)

    }

    @Test
    fun C6_dropListsTopMenu() {
        hoveR("Кнопка_меню_Кино", 1)
        shHaves("Текст_выпадающего_меню", "Боевик", 2)
        passList("Все_пункты_выпадающего_меню", "Кнопка_меню_Кино", 3)


        hoveR("Кнопка_меню_Сериалы", 4)
        shHaves("Текст_выпадающего_меню", "Cериалы", 5, 1)
        passList("Все_пункты_выпадающего_меню", "Кнопка_меню_Сериалы", 6)

        hoveR("Кнопка_меню_ТВ_шоу", 7)
        shHaves("Текст_выпадающего_меню", "Stand up шоу", 8, 2)
        passList("Все_пункты_выпадающего_меню", "Кнопка_меню_ТВ_шоу",9)

        hoveR("Кнопка_меню_ТВ_каналы", 10)
        shHaves("Текст_выпадающего_меню", "Музыкальные телеканалы", 11, 3)
        passList("Все_пункты_выпадающего_меню", "Кнопка_меню_ТВ_каналы",12)

        hoveR("Кнопка_меню_Детям", 13)
        shHaves("Текст_выпадающего_меню", "Детское ТВ", 14, 4)
        passList("Все_пункты_выпадающего_меню", "Кнопка_меню_Детям",15)

    }

    //    Метод клик
    fun elClick(key: String, step: Int) {
        val valElem = this.elem[key]
        if (valElem != null) {
            valElem.click()
        }
        println("Шаг $step Клик элемента $key произведен")
    }

    //    Метод подтверждения наличия текста в SelenideElement
    fun shHave(key: String, tex: String, step: Int) {
        val valElem = this.elem[key]
        if (valElem != null) {
            valElem.shouldHave(text(tex))
        }
        println("Шаг $step Наличие текста $tex в элементе $key подтверждено")
    }

    //    Метод подтверждения наличия текста в ElementsCollection
    fun shHaves(key: String, tex: String, step: Int, index: Int = 0) {
        val valElem = this.elems[key]
        if (valElem != null) {
            valElem[index].shouldHave(text(tex))
        }
        println("Шаг $step Наличие текста $tex в элементе $key подтверждено")
    }

    //    Метод наведения курсора на элемент
    fun hoveR(key: String, step: Int) {
        val valElem = this.elem[key]
        if (valElem != null) {
            valElem.hover()
        }
        println("Шаг $step Курсор наведен на элемент $key ")
    }

    //    Метод перебора ссылок ElementsCollection
    fun passList(key: String, pMenu: String, step: Int) {
        var valElems: ElementsCollection? = this.elems[key]
        if (valElems != null) {
            var numbers: MutableList<SelenideElement?> = (valElems)
            var newNum = numbers.toMutableList()
//            val siN = newNum.size
//            println("Количество ссылок newNum = $siN")
//            println("Вывод newNum")
//            println(newNum)
//            println("Начало цикла")
            var i: Int = 0
            loop_1@while (i != newNum.size)
//            loop_1@ for (i in newNum.indices)
            {
                        val strPoint = newNum[i]?.text()
                        if (strPoint != "") {
                            println("$i  $strPoint")
                            println(newNum[i]?.attr("href"))
//          Переход по ссылке
                            newNum[i]?.click()
//          Проверка перехода по заголовку
                            if (strPoint != null) {
                                shHaves("Текст_подзаголовка_страницы", strPoint, 5)
                            }
                            hoveR(pMenu, 1)

                            i = i + 1
                        }
                        else {
//                            println("удалено   $i  strPoint")
                            newNum.removeAt(i)
//                            println(newNum.size)
                            continue@loop_1
                        }

            }
            println("Конец цикла")
            println("Вывод newNum")
            println(newNum)
        }
    }

}