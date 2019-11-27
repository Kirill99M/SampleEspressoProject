package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import com.moonpi.swiftnotes.pages.MainPage
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.pages.EditPage
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step


class Tests : AbstractSwiftnotesTest() {
    private val editPage = EditPage()
    private val mainPage = MainPage()
    private val noteText = "write tests"
    private val titleText = "Homework"
    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка выхода из заметки без сохранения")
    fun exitFromHintWithoutSave() {
        step("Запуск приложения") {
            rule.launchActivity()
            deviceScreenshot("page_display")
        }
        step("Создание заметки") {
            mainPage.createNote()
            deviceScreenshot("page_display")
        }
        step("Проверка заголовка Title") {
            editPage.checkHintTitle()
            deviceScreenshot("page_display")
        }
        step("Проверка заголовка Note") {
            editPage.checkHintNote()
            deviceScreenshot("page_display")
        }
        step("Выход из заметки") {
            editPage.exitNote()
            deviceScreenshot("page_display")
        }
        step("Проверка диалога") {
            editPage.checkDialogSaveChanges()
            deviceScreenshot("page_display")
        }
        step("Клик No в диалоге") {
            editPage.clickNoinSaveChangesDialog()
            deviceScreenshot("page_display")
        }
        step("Проверка открытия главного экрана") {
            mainPage.checkMainScreen()
            deviceScreenshot("page_display")
        }
    }

    @Test
    @DisplayName("Проверка сохранения заметки")
    fun exitFromHintWithSave() {
        step("Запуск приложения") {
            rule.launchActivity()
            deviceScreenshot("page_display")
        }
        step("Создание заметки") {
            mainPage.createNote()
            deviceScreenshot("page_display")
        }
        step("Ввод текста в Title") {
            editPage.inputTextTitle(titleText)
            deviceScreenshot("page_display")
        }
        step("Ввод текста в Note") {
            editPage.inputTextNote(noteText)
            deviceScreenshot("page_display")
        }
        step("Проверка корректного текста в Title") {
            editPage.checkTextTitle(titleText)
            deviceScreenshot("page_display")
        }
        step("Проверка корректного текста в Note") {
            editPage.checkTextNote(noteText)
            deviceScreenshot("page_display")
        }
        step("Выход из заметки") {
            editPage.exitNote()
            deviceScreenshot("page_display")
        }
        step("клик Yes в диалоге сохранения") {
            editPage.clickYesInSaveChangesDialog()
            deviceScreenshot("page_display")
        }
        step("Проверка сохраненной заметки на главном экране") {
            step("Проверка перехода на главный экран") {
                mainPage.checkMainScreen()

            }
            step("Проверка отображения title") {
                mainPage.checkTextTitleOnMain(titleText)

            }
            step("Проверка отображения note") {
                mainPage.checkTextNoteOnMain(noteText)

            }
            deviceScreenshot("page_display")

        }
    }

    @Test
    @DisplayName("Проверка пунктов меню в тулбаре")
    fun checkMenuInToolbar() {
        step("Запуск приложения") {
            rule.launchActivity()
            deviceScreenshot("page_display")
        }
        step("Клик на меню на главной странице") {
            mainPage.pressMenu()
            deviceScreenshot("page_display")
        }
        step("Проверка пунктов меню на главной странице") {
            step("Проверка Backup notes") {
                mainPage.checkMenu("Backup notes")
                deviceScreenshot("page_display")
            }
            step("Проверка Restore notes") {
                mainPage.checkMenu("Restore notes")
                deviceScreenshot("page_display")
            }
            step("Проверка Rate app") {
                mainPage.checkMenu("Rate app")
                deviceScreenshot("page_display")
            }
        }
        step("Создание новой заметки") {
            onView(allOf(withId(R.id.title), withText("Rate app"))).perform(pressBack())
            mainPage.createNote()
            deviceScreenshot("page_display")
        }
        step("Клик на меню на странице редактирования") {
            editPage.pressMenu()
            deviceScreenshot("page_display")
        }
        step("Проверка пунктов меню на странице редактирования") {
            step("Проверка Note font size") {
                editPage.checkMenu("Note font size")
                deviceScreenshot("page_display")
            }
            step("Проверка Hide note body in list") {
                editPage.checkMenu("Hide note body in list")
                deviceScreenshot("page_display")
            }
        }
    }

    @Test
    @DisplayName("Проверка удаления заметки")
    fun checkDeleteNote() {
        step("Запуск приложения") {
            rule.launchActivity()
            deviceScreenshot("page_display")
        }
        step("Создание новой заметки") {
            mainPage.createNote()
            deviceScreenshot("page_display")
        }
        step("Ввод текста в Title") {
            editPage.inputTextTitle(titleText)
            deviceScreenshot("page_display")
        }
        step("Ввод текста в Note") {
            editPage.inputTextNote(noteText)
            deviceScreenshot("page_display")
        }
        step("Выход из заметки") {
            editPage.exitNote()
            deviceScreenshot("page_display")
        }
        step("клик Yes в диалоге сохранения") {
            editPage.clickYesInSaveChangesDialog()
            deviceScreenshot("page_display")
        }
        step("Удаление заметки") {
            mainPage.deleteNote(titleText)
            deviceScreenshot("page_display")
        }
        step("Проверка удаления заметки") {
            mainPage.checkDeleted(titleText)
            deviceScreenshot("page_display")
        }

    }


}

