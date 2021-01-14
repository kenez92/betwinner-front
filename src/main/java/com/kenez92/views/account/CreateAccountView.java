package com.kenez92.views.account;

import com.kenez92.config.Consts;
import com.kenez92.domain.account.UserDto;
import com.kenez92.service.account.AccountService;
import com.kenez92.views.MainLayout;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Route(value = "account/create", layout = MainLayout.class)
@PageTitle(Consts.CREATE_ACCOUNT_PAGE_TITLE)
public class CreateAccountView extends VerticalLayout {
    private static final String FIELD_SIZE = "150px";
    private static final String USER_START_MONEY = "100";
    private static final String MARGIN_RIGHT = "marginRight";
    private static final String MARGIN_RIGHT_VALUE = "10px";
    private static final String ROLE_ACCOUNT = Consts.ROLE_USER;
    private final Binder<UserDto> binder = new Binder<>();
    private final FormLayout formLayout = new FormLayout();
    private final UserDto userDto = new UserDto();
    private final AccountService accountService;

    public CreateAccountView(AccountService accountService) {
        this.accountService = accountService;
        createForm();
    }

    public void createForm() {
        TextField firstName = createTextField(Consts.FIRST_NAME);
        TextField lastName = createTextField(Consts.LAST_NAME);
        TextField login = createTextField(Consts.LOGIN);
        TextField eMail = createTextField(Consts.E_MAIL);
        PasswordField password = createPasswordField();
        ComboBox<String> strategyComboBox = createStrategyComboBox();
        Checkbox checkbox = new Checkbox();
        Label infoLabel = new Label();

        formLayout.addFormItem(firstName, Consts.FIRST_NAME);
        formLayout.addFormItem(lastName, Consts.LAST_NAME);
        formLayout.addFormItem(login, Consts.LOGIN);
        formLayout.addFormItem(password, Consts.PASSWORD);
        formLayout.addFormItem(eMail, Consts.E_MAIL);
        formLayout.addFormItem(strategyComboBox, Consts.STRATEGY);
        formLayout.addFormItem(checkbox, Consts.STRATEGY_CHECK_BOX_SUBSCRIPTION);

        textFieldBindig(firstName);
        textFieldBindig(lastName);
        textFieldBindig(login);
        textFieldBindig(eMail);
        passwordFieldBinding(password);
        binder.bind(strategyComboBox, UserDto::getUserStrategy, UserDto::setUserStrategy);
        binder.bind(checkbox, UserDto::getSubscription, UserDto::setSubscription);

        NativeButton save = new NativeButton(Consts.BUTTON_SAVE_NAME);
        NativeButton reset = new NativeButton(Consts.BUTTON_RESET_NAME);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(save, reset);
        save.getStyle().set(MARGIN_RIGHT, MARGIN_RIGHT_VALUE);
        save.addClickListener(event -> {
            if (binder.writeBeanIfValid(userDto)) {
                userDto.setRole(ROLE_ACCOUNT);
                userDto.setMoney(USER_START_MONEY);
                UserDto createdUserDto = accountService.createUser(userDto);
                if (createdUserDto != null) {
                    Notification.show(Consts.ACCOUNT_SUCCESSFUL_CREATED);
                } else {
                    Notification.show(Consts.ACCOUNT_NOT_CREATED);
                }
            } else {
                BinderValidationStatus<UserDto> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses().stream()
                        .filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(". "));
                infoLabel.setText(Consts.THERE_ARE_ERRORS + errorText);
            }
        });
        reset.addClickListener(event -> {
            binder.readBean(null);
            checkbox.setValue(false);
            strategyComboBox.setValue(Consts.EVERYTHING_STRATEGY);
        });

        add(formLayout, buttons, infoLabel);
    }

    private void textFieldBindig(TextField textField) {
        SerializablePredicate<String> isNotEmptyPredicate = value ->
                !textField.getValue().trim().isEmpty();
        String id = textField.getId().orElse("");
        switch (id) {
            case Consts.FIRST_NAME:
                Binder.Binding<UserDto, String> firstNameBinding = binder.forField(textField)
                        .withValidator(isNotEmptyPredicate, id + Consts.CANNOT_BE_EMPTY)
                        .bind(UserDto::getFirstName, UserDto::setFirstName);
                textField.addValueChangeListener(event -> firstNameBinding.validate());
                break;
            case Consts.LAST_NAME:
                Binder.Binding<UserDto, String> lastNameBinder = binder.forField(textField)
                        .withValidator(isNotEmptyPredicate, id + Consts.CANNOT_BE_EMPTY)
                        .bind(UserDto::getLastName, UserDto::setLastName);
                textField.addValueChangeListener(event -> lastNameBinder.validate());
                break;
            case Consts.LOGIN:
                Binder.Binding<UserDto, String> loginBinder = binder.forField(textField)
                        .withValidator(isNotEmptyPredicate, id + Consts.CANNOT_BE_EMPTY)
                        .bind(UserDto::getLogin, UserDto::setLogin);
                textField.addValueChangeListener(event -> loginBinder.validate());
                break;
            case Consts.E_MAIL:
                Binder.Binding<UserDto, String> eMailBinding = binder.forField(textField)
                        .withValidator(isNotEmptyPredicate, id + Consts.CANNOT_BE_EMPTY)
                        .withValidator(new EmailValidator(Consts.ERR_INCORRECT_EMAIL))
                        .bind(UserDto::getEmail, UserDto::setEmail);
                textField.addValueChangeListener(event -> eMailBinding.validate());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void passwordFieldBinding(PasswordField passwordField) {
        Binder.Binding<UserDto, String> eMailBinding = binder.forField(passwordField)
                .withValidator(new StringLengthValidator(
                        Consts.ERR_INCORRECT_PASSWORD, 5, null))
                .bind(UserDto::getPassword, UserDto::setPassword);
        passwordField.addValueChangeListener(event -> eMailBinding.validate());
    }

    private ComboBox<String> createStrategyComboBox() {
        ComboBox<String> strategyComboBox = new ComboBox<>();
        List<String> strategyList = new ArrayList<>();
        strategyList.add(Consts.EVERYTHING_STRATEGY);
        strategyList.add(Consts.DEFENSIVE_STRATEGY);
        strategyList.add(Consts.AGGRESSIVE_STRATEGY);
        strategyList.add(Consts.NORMAL_STRATEGY);
        strategyComboBox.setItems(strategyList);
        strategyComboBox.setValue(strategyList.get(0));
        return strategyComboBox;
    }

    private PasswordField createPasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(FIELD_SIZE);
        return passwordField;
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setMaxWidth(FIELD_SIZE);
        textField.setRequiredIndicatorVisible(true);
        textField.setId(id);
        return textField;
    }
}
