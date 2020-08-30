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



@TestInstance(TestInstance.Lifecycle.PER_CLASS)



class VallyTest {
    var elem: Map<String, SelenideElement> = mapOf(
        "Кнопка_меню_Кино" to elements(By.tagName("a")).findBy(text("Кино")),
        "Кнопка_меню_Сериалы" to elements(By.tagName("a")).findBy(text("Сериалы")),
        "Текст_подзаголовка_страницы" to element(By.xpath("//div[@class='site-page-header']//span//h2")),
    )

    @BeforeAll fun setUp() {
//        System.setProperty("selenide.browser", "chrome")
//        System.setProperty("selenide.browser", "firefox")
        Configuration.headless = false
        open("https://vally.tv")

    }

    @Test
    fun categoryMainPage() {
        val cl1 = elem["Кнопка_меню_Кино"]
        if (cl1 != null) {
            elClick(cl1)
        }

        val sh1 = elem["Текст_подзаголовка_страницы"]
        if (sh1 != null) {
            shHave(sh1, "Кино")
        }

        val cl2 = elem["Кнопка_меню_Сериалы"]
        if (cl2 != null) {
            elClick(cl2)
        }


        if (sh1 != null) {
            shHave(sh1, "Сериалы")
        }
    }

    fun elClick(element: SelenideElement) {
        element.click()
        println("Клик элемента $element произведен")
    }

    fun shHave(element: SelenideElement, tex: String) {
        element.shouldHave(text(tex))
        println("Наличие текста $tex в элементе $element подтверждено")
    }

}