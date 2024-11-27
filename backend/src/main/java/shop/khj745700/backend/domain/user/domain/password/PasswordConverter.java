package shop.khj745700.backend.domain.user.domain.password;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@Converter(autoApply = true)
public class PasswordConverter implements AttributeConverter<Password, byte[]> {
    private static PasswordEncoder encoder;

    static void setPasswordEncoder(PasswordEncoder encoder) {
        PasswordConverter.encoder = encoder;
    }
    @Override
    public byte[] convertToDatabaseColumn(Password password) {
        if(password == null) {
            return null;
        }
        if (password.isEncoded()) {
            return password.getValue().getBytes(StandardCharsets.UTF_8);
        }
        return encoder.encode(password.getValue()).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Password convertToEntityAttribute(byte[] s) {
        if(s == null) {
            return null;
        }
        return Password.of(new String(s, StandardCharsets.UTF_8), true);
    }
}
