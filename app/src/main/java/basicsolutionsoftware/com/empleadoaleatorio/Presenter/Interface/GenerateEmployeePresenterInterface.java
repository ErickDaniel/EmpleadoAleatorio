package basicsolutionsoftware.com.empleadoaleatorio.Presenter.Interface;

public interface GenerateEmployeePresenterInterface {

    String getNssAleatory();

    void getNombreAleatorio(int sexo);

    void getSegundoNombreAleatorio(int sexo);

    void getApellidoPaternoAleatorio();

    void getApellidoMaternoAleatorio();

    void getEdadAleatorio();

    int getSexoAleatorio();

    void generateCURPAleatorio(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, int sexo, String codigoEntidad);

    void getEntidadAleatorio();

    String getAnioRegistro(String fechaNacimiento);

    String getLocalidadAleatoria();

    String getMunicipioAleatorio();

    String getSeccionAleatoria();

    String getEmision(String anioRegistro);

    String getURLFotografia(int sexo);

    void getCalle();

    void getColonia();

    String getDomicilio(String colonia, String calle, String entidadNacimiento);
}
