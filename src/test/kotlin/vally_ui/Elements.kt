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

class Elements {
    var elem: Map<String, SelenideElement> = mapOf(
            "Кнопка_меню_Кино" to elements(By.tagName("a")).findBy(text("Кино")),
            "Кнопка_меню_Сериалы" to elements(By.tagName("a")).findBy(text("Сериалы")),
            "Текст_подзаголовка_страницы" to element(By.xpath("//div[@class='site-page-header']//span//h2")),
    )

    @Test
    fun copyElem(elements: Elements) {
        val result = Elements()
        result.elem = elements.elem
        println(result)
    }
}





