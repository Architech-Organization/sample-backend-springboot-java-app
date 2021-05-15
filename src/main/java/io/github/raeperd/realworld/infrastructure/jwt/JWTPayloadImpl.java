package io.github.raeperd.realworld.infrastructure.jwt;

import io.github.raeperd.realworld.domain.jwt.JWTPayload;
import io.github.raeperd.realworld.domain.user.User;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.time.Instant.now;

class JWTPayloadImpl implements JWTPayload {

    private final long sub;
    private final String name;
    private final long iat;

    static JWTPayloadImpl of(User user, long epochSecondExpired) {
        return new JWTPayloadImpl(user.getId(), valueOf(user.getEmail()), epochSecondExpired);
    }

    JWTPayloadImpl(long sub, String name, long iat) {
        this.sub = sub;
        this.name = name;
        this.iat = iat;
    }

    @Override
    public long getUserId() {
        return sub;
    }

    @Override
    public boolean isExpired() {
        return iat < now().getEpochSecond();
    }

    @Override
    public String toString() {
        return format("{\"sub\":%d,\"name\":\"%s\",\"iat\":%d}", sub, name, iat);
    }
}