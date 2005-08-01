package net.sourceforge.stripes.config;

import net.sourceforge.stripes.controller.ActionBeanPropertyBinder;
import net.sourceforge.stripes.controller.ActionResolver;
import net.sourceforge.stripes.localization.LocalizationBundleFactory;
import net.sourceforge.stripes.localization.LocalePicker;
import net.sourceforge.stripes.validation.TypeConverterFactory;

/**
 * <p>Type safe interface for accessing configuration information used to configure Stripes. All
 * Configuration implementations are handed a reference to the BootstrapPropertyResolver to
 * enable them to find initial values and fully initialize themselves.  Through the
 * BootstrapPropertyResolver implementations also get access to the ServletConfig of the
 * DispatcherServlet which can be used for locating configuration values if desired.</p>
 *
 * <p>Implementations of Configuration should fail fast.  At initialization time they should
 * detect as many failures as possible and raise an exception.  Since exceptions in Configuration
 * are considered fatal there are no exception specifications and implementations are expected to
 * throw runtime exceptions with plenty of details about the failiure and its suspected cause(s).</p>
 *
 * @author Tim Fennell
 */
public interface Configuration {
    /**
     * Supplies the Configuration with a BootstrapPropertyResolver. This method is guaranteed to
     * be invoked prior to the init method.
     * 
     * @param resolver a BootStrapPropertyResolver which can be used to find any values required
     *        by the Configuration in order to initialize
     */
    void setBootstrapPropertyResolver(BootstrapPropertyResolver resolver);

    /**
     * Called by the DispatcherServlet to initialize the Configuration. Any operations which may
     * fail and cause the Configuration to be inaccessible should be performed here (e.g.
     * opening a configuration file and reading the contents).
     */
    void init();

    /**
     * Implementations should implement this method to simply return a reference to the
     * BootstrapPropertyResolver passed to the Configuration at initialization time.
     *
     * @return BootstrapPropertyResolver the instance passed to the init() method
     */
    BootstrapPropertyResolver getBootstrapPropertyResolver();

    /**
     * Returns an instance of ActionResolver that will be used by Stripes to lookup and resolve
     * ActionBeans.  The instance should be cached by the Configuration since multiple entities
     * in the system may access the ActionResolver throughout the lifetime of the application.
     *
     * @return the Class representing the configured ActionResolver
     */
    ActionResolver getActionResolver();

    /**
     * Returns an instance of ACtionBeanPropertyBinder that is responsible for binding all
     * properties to all ActionBeans at runtime.  The instance should be cached by the Configuration
     * since multiple entities in the system may access the ActionBeanPropertyBinder throughout the
     * lifetime of the application.
     *
     * @return ActionBeanPropertyBinder the property binder to be used by Stripes
     */
    ActionBeanPropertyBinder getActionBeanPropertyBinder();

    /**
     * Returns an instance of TypeConverterFactory that is responsible for providing lookups and
     * instances of TypeConverters for the validation system.  The instance should be cached by the
     * Configuration since multiple entities in the system may access the TypeConverterFactory
     * throughout the lifetime of the application.
     *
     * @return TypeConverterFactory an instance of a TypeConverterFactory implementation
     */
    TypeConverterFactory getTypeConverterFactory();

    /**
     * Returns an instance of LocalizationBundleFactory that is responsible for looking up
     * resource bundles for the varying localization needs ot a web application. The instance should
     * be cached by the Configuration since multiple entities in the system may access the
     * LocalizationBundleFactory throughout the lifetime of the application.
     *
     * @return LocalizationBundleFactory an instance of a LocalizationBundleFactory implementation
     */
    LocalizationBundleFactory getLocalizationBundleFactory();

    /**
     * Returns an instance of LocalePicker that is responsible for choosing the Locale for
     * each request that enters the system.
     *
     * @return LocalePicker an instance of a LocalePicker implementation
     */
    LocalePicker getLocalePicker();
}
